import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

function random(n) {
  let res = Math.random() * n;
  return Math.floor(res)

}


class Card extends Component {

  constructor(props) {
    super(props);
    this.state = { champvisible: true };
  }
  changevisibility = () => {
    this.setState({ champvisible: !(this.state.champvisible) })
  }

  render() {
    return (<div className='Card'> {this.state.champvisible ? this.props.symbol : "--"}
      <button className='turn' onClick={this.changevisibility}></button>

    </div>);
  }
}

/*
class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    );
  }
}
*/



class MainPage extends Component {

  constructor(connexion) {
    super();
    this.pagecourant = connexion;
    this.state = { isConnected: true };
  }

  getConnected = () => {
    this.state.isConnected = true;
  }

  setLogout = () => {
    this.state.isConnected = false;

  }

  render() {

    let cards = ["A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"];

    return (
      <div className="MainPage">
        <header className="MainPage-header">
          <p>{this.state.isConnected}</p>
          <NavigationPannel login={this.getConnected} logout={this.setLogout} isConnected={this.state.isConnected}></NavigationPannel>

          {cards.map(s => <Card symbol={s}></Card>)}
          <img src={logo} className="MainPage-logo" alt="logo" />
          <p>{this.connexion}</p>

        </header>
      </div>
    );
  }
}

class NavigationPannel extends Component {

  constructor(login, logout, connected) {
    super();
    this.login = login;
    this.logout = logout;
    this.state = { isConnected: connected };
  }

  render() {
    return (
      <div>
        <p>............................</p>
        {(this.state.isConnected)?(<Logout></Logout>):(<Login></Login>)}
        <p>............................</p>
      </div>
    )
  }
}

class Logout extends Component{
  constructor(logout){
    this.logout = logout;
  }

  render(){
    return (<button onClick={this.logout()}>Deconnexion</button>);
  }
}

class Login extends Component{
  render(){
    return (
      <form action="/" method="POST">
        <label for="login">login:</label>
        <input type="text" name="login"></input>
        <label for="mdp">password:</label>
        <input type="password" name="mdp"></input>
        <input type="submit" value="Connexion"></input>
      </form>
    );
  }
}

export default MainPage;