import React, { useState, useEffect, useRef } from 'react'

function HookTimer() {
  const [timer, setTimer] = useState(0)
  // If we declare timer outside useEffect, when the function renders the timer variable will be new 
  // because every time function is called it creates its local variables and then they get garbage collected, 
  // by using useRef the timer value never gets garbage collected and the clear intervals finds the original value

  // tại sao dùng useRef mà ko dùng useState thì
  // xem giải thích ở file HookTimerState
  const interValRef = useRef()

  useEffect(() => {
    interValRef.current = setInterval(() => {
      setTimer(timer => timer + 1)
    }, 1000000)

    return () => {
      clearInterval(interValRef.current)
    }
  }, [])

  return (
    <div>
      {console.log('REF')}
      HookTimer - {timer} -
      <button onClick={() => clearInterval(interValRef.current)}>Clear Timer</button>
    </div>
  )
}

export default HookTimer
