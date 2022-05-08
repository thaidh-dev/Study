import React, { useState, useEffect, useRef } from 'react'

function HookTimerState() {
  const [timer, setTimer] = useState(0)
  const [interValState, setInterValState] = useState(0)

  useEffect(() => {
    // render xong sẽ nhảy vào useEffect
    // do phải setInterValState nên sẽ phải rerender 
    // -> console.log('STATE') sẽ xuất hiện 2 lần, phải render 2 lần
    setInterValState(setInterval(() => {
      setTimer(timer => timer + 1)
    }, 1000000))

    return () => {
      clearInterval(interValState)
    }
  }, [])

  return (
    <div>
      {console.log('STATE')}
      HookTimerState - {timer} -
      <button onClick={() => clearInterval(interValState)}>Clear Timer</button>
    </div>
  )
}

export default HookTimerState
