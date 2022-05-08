import React, { useState, useCallback } from 'react'
import Count from './Count'
import Button from './Button'
import Title from './Title'

function ParentComponent() {
	const [age, setAge] = useState(25)
	const [salary, setSalary] = useState(50000)

	const incrementAge = useCallback(() => {
		setAge(age + 1)
	}, [age])

	// đĩnh nghĩa Reference equality: Reference equality means that two object references refer to the same underlying object.
	// This can occur through simple assignment.
	// two objects are created, but after the assignment statement, both references refer to the same object.

	// mỗi lần state của ParentComponent thay đổi, ParentComponent sẽ đc rerender
	// tất cả các child component của ParentComponent (bao gồm Tittle, Count, Button) cũng sẽ đc rerender
	// do đó phải sử dụng React.memo để các child component sẽ chỉ rerender nếu state or props truyền vào nó thay đổi

	// 2 func incrementAge và incrementSalary sẽ đc create mỗi lần ParentComponent rerender
	// the func before the rerender is different to the func after the rerender
	// do đó: incrementAge & incrementSalary là prop của 2 component botton 
	// nên React.memo sẽ hiểu là prop đã đc change
	// nên Button component sẽ đc rerender mỗi lần state của ParentComponent change
	// để prevent cái sự retard này, useCallback đc sinh ra

	// useCallback is a hook that will return a memoized version of the callback func that only changes if one of the dependencies has changed
	// instance func đc truyền vào useCallback sẽ đc lưu vào 1 chỗ in memory, và nếu state slary ko thay đổi thì incrementSalary sẽ luôn reference tới chỗ memory đó.
	const incrementSalary = useCallback(() => {
		setSalary(salary + 1000)
	}, [salary])

	return (
		<div>
			<Title />
			<Count text="Age" count={age} />
			<Button handleClick={incrementAge}>Increment Age</Button>
			<Count text="Salary" count={salary} />
			<Button handleClick={incrementSalary}>Increment Salary</Button>
		</div>
	)
}

export default ParentComponent
