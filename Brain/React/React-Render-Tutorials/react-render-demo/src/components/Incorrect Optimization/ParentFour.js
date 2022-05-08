import React, { useState, useMemo, useCallback } from 'react'
import { MemoizedChildFive } from './ChildFive'

export const ParentFour = () => {
  const [count, setCount] = useState(0)
  const [name, setName] = useState('Vishwas')

  const person = {
    fname: 'Bruce',
    lname: 'Wayne',
  }

  const memoizedPerson = useMemo(() => person, [])

  const handleClick = () => {}

  const memoizedHandleClick = useCallback(handleClick, [])

  console.log('ParentFour Render')
  return (
    <div>
      <button onClick={() => setName('Codevolution')}>Change name</button>
      <button onClick={() => setCount(c => c + 1)}>Count - {count}</button>
      {/* every time the parent component renders, 
      a new person object reference and a new handleClick func is created 
      and then passed in as props to the child component
      if the reference changed react memo cannot optimize
      and hence the child also re-renders */}
      {/* <MemoizedChildFive name={name} person={memoizedPerson} /> */}
      <MemoizedChildFive name={name} handleClick={memoizedHandleClick} />
    </div>
  )
}
