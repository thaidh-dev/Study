import React, { useState } from 'react'

export const UseState = () => {
  const [count, setCount] = useState(0)

  console.log('UseState Render')
  return (
    <div>
      <button onClick={() => setCount(c => c + 1)}>Count - {count}</button>
      {/* after the initial render, if u call a setter func but set the state to the same value, the component will not re-render */}
      <button onClick={() => setCount(0)}>Count to 0</button>
      {/* after the component has been re-rendered, if u set the state to the same value, the component will re-render but only one time
      React can’t guess the ouput of render() won’t change: it has to render() again and compare the results with the previous render() */}
      <button onClick={() => setCount(5)}>Count to 5</button>
    </div>
  )
}

// React components automatically re-render whenever there is a change in their state or props. 
// A simple update of the state, from anywhere in the code, causes all the User Interface (UI) elements to be re-rendered automatically