import React, { Component } from 'react'
import ComponentE from './ComponentE'
import { UserProvider } from './userContext'

export class ComponentC extends Component {
	render() {
		return (
			<div>
				<UserProvider value='ComponentC đang đc wrap = UserProvider, thì cái đoạn string này chính là context'>
					<ComponentE />
				</UserProvider>
			</div>
		)
	}
}

export default ComponentC
