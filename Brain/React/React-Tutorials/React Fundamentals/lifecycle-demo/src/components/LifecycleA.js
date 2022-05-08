import React, { Component } from 'react'
import LifecycleB from './LifecycleB'

class LifecycleA extends Component {
	constructor(props) {
		super(props)
		this.state = {
			name: 'Vishwas'
		}
		console.log('LifecycleA constructor')
	}

	static getDerivedStateFromProps(props, state) {
		console.log('LifecycleA getDerivedStateFromProps')
		state.name = 'thaidh'
		return state;
	}

	shouldComponentUpdate() {
		console.log('LifecycleA shouldComponentUpdate')
		return true
	}

	changeState = () => {
		this.setState({
			name: 'Codevolution'
		})
		console.log('LifecycleA changeState');
	}

	render() {
		console.log('LifecycleA render')
		return (
			<div>
				<p>{this.state.name}</p>
				<button onClick={this.changeState}>Change state</button>
				LifecycleA
				<LifecycleB />
			</div>
		)
	}

	getSnapshotBeforeUpdate(prevProps, prevState) {
		console.log('LifecycleA getSnapshotBeforeUpdate')
		return prevState
	}

	componentDidUpdate(prevProps, prevState, snapshot) {
		// snapshot là value return từ getSnapshotBeforeUpdate
		console.log('LifecycleA componentDidUpdate')
	}

	componentDidMount() {
		console.log('LifecycleA componentDidMount')
	}
}

export default LifecycleA
