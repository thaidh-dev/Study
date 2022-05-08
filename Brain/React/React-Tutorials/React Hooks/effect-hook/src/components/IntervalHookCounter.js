import React, {useState, useEffect} from 'react'

function IntervalHookCounter() {
  const [count, setCount] = useState(0)

  const tick = () => {
    setCount(prevCount => prevCount + 1)
  }
  // If you think dependency array is a way to specify when you want to rerun the effect, you are going to run into problems. Instead, dependency array should be thought of as a way to let react know about everything that the effect must watch for changes.
  useEffect(() => {
    console.log('useEffect');
    const interval = setInterval(tick, 1000)
    return () => {
      clearInterval(interval)
    }
	}, [])
  return (
    <div>
      {count}
    </div>
  )
}

export default IntervalHookCounter
