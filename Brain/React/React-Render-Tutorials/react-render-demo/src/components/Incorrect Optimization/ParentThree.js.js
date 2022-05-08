import React, { useState } from 'react'
import { ChildThree, MemoizedChildThree } from './ChildThree'
import { MemoizedChildFour } from './ChildFour'

export const ParentThree = () => {
  const [count, setCount] = useState(0)
  const [name, setName] = useState('Vishwas')

  console.log('ParentThree Render')
  return (
    <div>
      <button onClick={() => setName('Codevolution')}>Change name</button>
      <button onClick={() => setCount(c => c + 1)}>Count - {count}</button>
      {/* <ChildThree name={name} /> */}
      {/* MemoizedChildThree chỉ re-render khi so sánh shallow comparison (SC) và thấy khác
      children prop là text 'Hello': so sánh SC return true (ko khác) -> MemoizedChildThree ko re-render
      children prop là <strong>: a new DOM has been created for <strong> -> SC return false (khác) -> MemoizedChildThree re-render */}
      {/* <MemoizedChildThree name={name}>
        <strong>Hello</strong>
      </MemoizedChildThree> */}
      <MemoizedChildFour name={name} />
    </div>
  )
}
