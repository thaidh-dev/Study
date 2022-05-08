import React from 'react'

export const ChildTwo = ({ name }) => {
  console.log('ChildTwo Render')
  return <div>ChildTwo component - {name}</div>
}

// in react, when parent component render, child component might uncessarily render
// to optimize this behaviour, u can use React.memo and pass in child component
// React.memo will perform a shallow comparison of the prev and new props and re-render the child conponent only if the props have changed
export const MemoizedChildTwo = React.memo(ChildTwo)