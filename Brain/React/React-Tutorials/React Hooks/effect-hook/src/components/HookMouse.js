import React, { useState, useEffect } from 'react'

function HookMouse() {
	const [x, setX] = useState(0)
	const [y, setY] = useState(0)

	const logMousePosition = e => {
		console.log('Mouse event')
		setX(e.clientX)
		setY(e.clientY)
	}

	useEffect(() => {
		console.log('Không có tham số thứ truyền 2 vào là 1 array thì cái useEffect này sẽ đc gọi mỗi lần render lại');
	});

	useEffect(() => {
		console.log('useFffect called')
		window.addEventListener('mousemove', logMousePosition)

		// the func that is passed to useEffect can return a func which will be executed when the component will unmount 
		return () => {
			console.log('Component unmounting code')
			window.removeEventListener('mousemove', logMousePosition)
		}
	}, [] // mimic componentDidMount with useEffect hook by simply passing in an empty array as the second param, react will call this effect only on initial render and forget about it
	)

	return (
		<div>
			Hooks - X - {x} Y - {y}
		</div>
	)
}

export default HookMouse
