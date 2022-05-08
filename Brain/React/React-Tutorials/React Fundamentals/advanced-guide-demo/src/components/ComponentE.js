import React, { Component } from 'react'
import ComponentF from './ComponentF'
import UserContext from './userContext'

export class ComponentE extends Component {
	// cách viết 1
	static contextType = UserContext;

	render() {
		return (
			<div>
				Component E context {this.context}
				<ComponentF />
			</div>
		)
	}
}

// cách viết 2
// ComponentE.contextType = UserContext

// so sánh cách viết trên (context type) vs comsumer component
// now u might think this look much simpler compared to the consumer components syntax
// why should we not just stick to context type
// there're 2 limitations 
// the first one is that it only work with class components 
// the second limitation is that u can only subscribe to a single context using context type. many a times in your application u need to read more than 1 context, in which scenario the consumer component is the way to go

export default ComponentE
