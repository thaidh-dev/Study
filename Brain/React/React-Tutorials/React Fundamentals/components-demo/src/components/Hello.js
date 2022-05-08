import React from 'react'

const Hello = () => {
  // with jsx syntax
  return (
    <div className='dummyClass'>
      <h1>Hello Vishwas</h1>
    </div>
  )

  // without jsx syntax
  // return React.createElement(
  //   'div',
  //   {id: 'hello', className: 'dummyClass'},
  //   React.createElement('h1', null, 'Hello Vishwas')
  // )
}

export default Hello
