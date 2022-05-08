import React from 'react'
import './App.css'
import FocusInput from './components/FocusInput'
import ClassTimer from './components/ClassTimer'
import HookTimer from './components/HookTimer'
import HookTimerState from './components/HookTimerState'

function App() {
	return (
		<div className="App">
			{/* <FocusInput /> */}
			<ClassTimer />
			<HookTimer />
			<HookTimerState />
		</div>
	)
}

export default App
