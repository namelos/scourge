import React from 'react'
import { render } from 'react-dom'

const socket = new WebSocket('ws://localhost:9000/socket')

const send = e => socket.send(e.target.value)

const SocketTester = ({ socket, message }) => <div>
  <input type="text" onChange={ send }/>
  <br/>
  <div>response: {message}</div>
</div>

socket.addEventListener('open', () => console.log('opened'))

socket.addEventListener('message', e =>
  render(<SocketTester socket={socket} message={e.data}/>,
    document.querySelector('#root')))

render(<SocketTester socket={socket} />, document.querySelector('#root'))