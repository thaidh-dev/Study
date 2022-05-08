import React from 'react'

function Button({ handleClick, children }) {
  console.log('Rendering button - ', children)
  return (
    <button onClick={handleClick}>
      {children}
    </button>
  )
}

// nếu ko sử dụng React.memo, thì 
// mỗi lần state của ParentComponent thay đổi, ParentComponent sẽ đc rerender
// tất cả các child component của ParentComponent (bao gồm Tittle, Count, Button) cũng sẽ đc rerender
// sử dụng React.memo thì các child component sẽ chỉ rerender nếu state or props truyền vào nó thay đổi
export default React.memo(Button)
