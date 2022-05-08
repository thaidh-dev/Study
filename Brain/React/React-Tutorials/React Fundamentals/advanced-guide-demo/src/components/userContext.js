import React from 'react'

const UserContext = React.createContext('nếu ComponentC ko wrap = UserProvider, thì cái đoạn string này chính là context')

const UserProvider = UserContext.Provider
const UserConsumer = UserContext.Consumer

export { UserProvider, UserConsumer }
export default UserContext;