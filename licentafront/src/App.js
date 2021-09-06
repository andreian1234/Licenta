import "./assets/scss/App.scss";
// import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
// import Home from "./views/Home";
// import Login from "./views/Login";
// import Register from "./views/Register";
import React, {Component} from "react";

class App extends Component {
  state = {
    isLoading: true,
    foods: [],
  };

  async componentDidMount() {
    const response = await fetch("/foods/find");
    const body = await response.json();
    this.setState({foods: body, isLoading: false});
  }

  render() {
    const {foods, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    // function App() {
    return (
        <div className="App">
          <header className="App-header">
            <div className="App-intro">
              <h2>JUG List</h2>
              {foods.map((food) => (
                  <div key={food.id}>{food.label}</div>
              ))}
            </div>
          </header>
          {/* <Router>
            <Switch>
              <Route exact path="/">
                <Login />
              </Route>
              <Route path="/register">
                <Register />
              </Route>
              <Route path="/home">
                <Home />
              </Route>
            </Switch> */}
          {/* </Router> */}
        </div>
    );
  }
}

// }

export default App;
