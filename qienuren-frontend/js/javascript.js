function gebruikersnaam() {
    var naam = "";
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (this.readyState == 4) {
        var obj = JSON.parse(this.responseText);
        naam = obj.voornaam + " " + obj.achternaam;
      }

      document.getElementById("gebruikersnaam").innerHTML = naam;

    }
    xhr.open("GET", "http://localhost:8082/api/users/" + localStorage.getItem("userId"), true);
    xhr.setRequestHeader("Authorization", localStorage.getItem("token"));
    xhr.send();
  }

  function bedrijfsnaam() {
    var naam = "";
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (this.readyState == 4) {
        var obj = JSON.parse(this.responseText);
        naam = obj.bedrijfsNaam;
      }

      document.getElementById("bedrijfsnaam").innerHTML = naam;

    }
    xhr.open("GET", "http://localhost:8082/api/users/bedrijf/" + localStorage.getItem("userId"), true);
    xhr.setRequestHeader("Authorization", localStorage.getItem("token"));
    xhr.send();
  }