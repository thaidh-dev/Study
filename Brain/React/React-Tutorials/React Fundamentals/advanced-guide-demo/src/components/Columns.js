import React from 'react'

function Columns() {
  return (
    // có thể đặt key vào React.Fragment như vd bên dưới
    // <React.Fragment key={item.id}>
    // <React.Fragment> = <></>
    // <></> thì ko pass key attr vào đc
    <React.Fragment>
      <td>Name</td>
      <td>Vishwas</td>
    </React.Fragment>
  )
}

export default Columns
