import React, { Component } from 'react'

class LifecycleB extends Component {

  constructor(props) {
    super(props)
    this.state = {
      name: 'Vishwas'
    }
    console.log('LifecycleB constructor')
  }

  static getDerivedStateFromProps(props, state) {
    console.log('LifecycleB getDerivedStateFromProps')
    return null
  }

  shouldComponentUpdate() {
    console.log('LifecycleB shouldComponentUpdate')
    return true
  }

  render() {
    console.log('LifecycleB render')
    return (
      <div>
        LifecycleB
      </div>
    )
  }

  getSnapshotBeforeUpdate(prevProps, prevState) {
    console.log('LifecycleB getSnapshotBeforeUpdate')
    return null
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    console.log('LifecycleB componentDidUpdate')
  }

  componentDidMount() {
    console.log('LifecycleB componentDidMount')
  }
}

export default LifecycleB
