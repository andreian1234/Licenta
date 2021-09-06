import "./assets/scss/App.scss";
// import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
// import Home from "./views/Home";
// import Login from "./views/Login";
// import Register from "./views/Register";
import React, {useEffect, useState} from "react";
import req from "./utils/Requests";

// class App extends Component {
//   state = {
//     isLoading: true,
//     foods: [],
//   };

//   async componentDidMount() {
//     const response = await fetch("/foods/find");
//     const body = await response.json();
//     this.setState({foods: body, isLoading: false});
//   }

//   render() {
//     const {foods, isLoading} = this.state;

//     if (isLoading) {
//       return <p>Loading...</p>;
//     }

function App() {
    const [isLoading, setIsLoading] = useState(true);
    const [foods, setFoods] = useState([]);

    useEffect(() => {
        // async function request() {
        //   const response = await fetch("/foods/find")
        //     .then((body) => body.json())
        //     .then((data) => {
        //       setFoods(data);
        //       setIsLoading(false);
        //     });
        // }
        // request();
    }, []);

    if (isLoading) {
        return (
            <div>
                <button onClick={() => req.login("andreian@gmail.com", "paroal1")}>
                    log 1
                </button>
                <button onClick={() => req.signOut()}>signout</button>
                <button onClick={() => req.login("andreia1n@gmail.com", "paroal1")}>
                    log 2
                </button>
                <button onClick={() => req.getFoods()}>GET FOODS</button>
            </div>
        );
    } else {
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
