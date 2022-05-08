import React from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { buyCake } from '../redux'

function HooksCakeContainer () {
  // useSelector is a hook the react Redux library provides which acts as a closed equivalent to the mapStateToProps function 
  // to get hold of any state that is maintained in the redux store we use the useSelector hook
  const numOfCakes = useSelector(state => state.cake.numOfCakes)
  // the use dispatch hook returns a reference to the dispatch function from the Redux store 
  const dispatch = useDispatch()
  return (
    <div>
      <h2>Number of cakes - {numOfCakes} </h2>
      <button onClick={() => dispatch(buyCake())}>Buy Cake</button>
    </div>
  )
}

export default HooksCakeContainer
