import React from 'react'
import { connect } from 'react-redux'
import { buyCake } from '../redux'

function CakeContainer (props) {
  return (
    <div>
      <h2>Number of cakes - {props.numOfCakes} </h2>
      <button onClick={props.buyCake}>Buy Cake</button>
    </div>
  )
}

// in the first function the state from the redux store is mapped to our component props 
// so apart from whatever props cake container was receiving 
// it will now receive an additional prop called numOfCakes which reflects the number of cakes in the redux store

// when you want to access the redux state in your component 
// you define the mapStateToProps function 
// it gets the Redux state as a parameter which can be used to retrieve the appropriate state properties
// in our case we map state.numOfCakes to a prop called numOfCakes which will then render in the JSX
const mapStateToProps = state => {
  return {
    numOfCakes: state.cake.numOfCakes
  }
}

// similarly mapDispatchToProps will basically map our dispatch of an action creator to a prop in our component 
// so our component now receives a second additional prop called buyCake which will basically dispatch the buy cake action

// for dispatching actions, we have the mapDispatchToProps function
// this function gets the dispatch method as a parameter and allows us to map action creators buyCake() to props in our component 
// in our example we mapped dispatch(buyCake()) to a prop called byCake 
// this allows us to call byCake as props.byCake 
const mapDispatchToProps = dispatch => {
  return {
    buyCake: () => dispatch(buyCake())
  }
}

// and all this is possible because of the connect function from react Redux
// the connect function connects a react component to the redux store 
// in our case it connects CakeContainer to the redux store
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CakeContainer)
