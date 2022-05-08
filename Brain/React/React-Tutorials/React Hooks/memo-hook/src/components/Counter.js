import React, { useState, useMemo, memo } from 'react'

function Counter() {
	const [counterOne, setCounterOne] = useState(0)
	const [counterTwo, setCounterTwo] = useState(0)

	const incrementOne = () => {
		setCounterOne(counterOne + 1)
	}

	const incrementTwo = () => {
		setCounterTwo(counterTwo + 1)
	}

	// khi bấm vào btn 'Count Two' -> state counterTwo thay đổi -> component Counter sẽ rerender -> func isEven sẽ đc call để show ra xem counterOne là even or odd
	// nhưng isEven phải chạy quả vòng while 2000000000 lần nên mất time, mà bấm vào btn 'Count Two' để tăng counterTwo thì đéo liên quan mẹ gì đến counterOne
	// dẫn đến việc tăng counterTwo cũng mất time theo
	// chính vì thế useMemo ra đời
	
	// func đc pass vào useMemo có return 1 value (gọi tạm là func F)
	// value đó sẽ đc lưu vào 1 chỗ in memory (gọi tạm là chỗ M)
	// khi isEven đc call, thì isEven sẽ luôn trỏ thẳng vào M mà ko cần mất time recalc cái logic bên trong (recalc F)
	// nếu state counterOne thay đổi, thì logic bên trong sẽ đc recalc (recalc F)

	// khác nhau giữa useCallback và useMemo
	// useCallback: Caches the provided function instance
	// useMemo: Invokes the provided function & caches the result
	// what do cache mean? it means store the object(function instance or the result) in memory, so you don't have to recreate it every time
	const isEven = useMemo(() => {
		let i = 0
		while (i < 2000000000) i++
		return counterOne % 2 === 0
	}, [counterOne])

	return (
		<div>
			<div>
				<button onClick={incrementOne}>Count One - {counterOne}</button>
				<span>{isEven ? 'Even' : 'Odd'}</span>
			</div>
			<div>
				<button onClick={incrementTwo}>Count Two - {counterTwo}</button>
			</div>
		</div>
	)
}

export default Counter
