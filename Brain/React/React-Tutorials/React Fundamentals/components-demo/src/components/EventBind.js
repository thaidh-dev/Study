import React, { Component } from 'react'

class EventBind extends Component {

  constructor() {
    super()
    this.state = {
      message: 'Hello'
    }
    // this.clickHandler = this.clickHandler.bind(this)
  }

  // clickHandler() {
  //   // nếu ko bind thì this trong named func sẽ undefined
  //   // Giải thích 1: lý do vì sao phải bind: First of all "this" keyword points the Parent element on which it is called. So when you use a Named function with the "this" keyword inside it, it isn't binded to anything at all. So the control passes to the "Window" element . So the Window element acts as a parent to the Named function . We see "Undefined" here because we use "Strict mode " in Javascript . What Strict Mode does is, if there is a named function( without any Bind method ) it transfers control to the parent element(window element) as i said ..this action is blocked by the strict mode ..So you see "undefined" in there ...However if you take off the strict mode ,you should see it will return the Window object ... The One Stop Solution to all of this is using arrow functions which takes parent element as the Class and not the window object.
  //   // Giải thích 2: lý do vì sao phải bind: 'this' keyword is undefined because this used with a function returns 'window' object on the browser and 'global' object inside nodejs environment. Since 'react strict mode' is enabled, it is returning 'undefined'.
  //   // Strict hiểu đơn giản theo nghĩa tiếng Việt là "nghiêm ngặt, nghiêm khắc". Strict Mode là một quy mẫu nghiêm khắc của Javascript. Nếu như coi việc viết code bình thường là Normal Mode, thì Strict Mode sẽ có thêm nhiều quy định khác so với Normal Mode. Việc đó khiến cho một thao tác vốn bình thường có thể chạy ngon lành trở nên lỗi, và throw ra errors.
  //   console.log(this)
  //   this.setState({message: 'Goodbye'})
  // }

  // The handling of this is also different in arrow functions compared to regular functions.
  // In short, with arrow functions there are no binding of this (ko cần bind).
  // In regular functions the this keyword represented the object that called the function, which could be the window, the document, a button or whatever.
  // With arrow functions, the this keyword always represents the object that defined the arrow function.
  clickHandler = () => {
    this.setState({message:'Goodbye'})
  }

  render() {
    return (
      <div>
        <div>{this.state.message}</div>
        <button onClick={this.clickHandler}>Click</button>
        {/* <button onClick={this.clickHandler.bind(this)}>Click</button> */}
        {/* dùng syntax như bên dưới đây thì ko cần phải bind(this), syntax này chính là arrow func */}
        {/* <button onClick={() => this.clickHandler()}>Click</button> */}
      </div>
    )
  }
}

export default EventBind