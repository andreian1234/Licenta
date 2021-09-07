class Requests {
  login(mail, password) {
    const data = {username: mail, password: password};

    fetch("/auth/userpass", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
        .then((response) => response.json())
        .then((data) => {
          localStorage.setItem("mail", mail);
          localStorage.setItem("token", data.token);
          console.log("logat");
        });
  }

  getFoods() {
    const token = localStorage.getItem("token");
    console.log(token);
    fetch("/foods/find", {
      method: "GET",
      headers: {
        Authorization: "Bearer " + token,
      },
    })
        .then((response) => response.json())
        .then((data) => console.log(data));
  }

  signOut() {
    console.log("delogat");
    localStorage.removeItem("token");
  }
}

export default new Requests();
