import React from 'react'

export const Child = () => {
  // after re-render, if the new state is the same as old state
  // react will render the Parent component just 1 more time but will not re-render the Child component
  console.log('Child Render')
  return <div>Child component</div>
}
