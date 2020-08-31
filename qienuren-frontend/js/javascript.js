function gebruikersnaam() {
    var naam = "";
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (this.readyState == 4) {
        var obj = JSON.parse(this.responseText);
        voornaam = "Hallo " + obj.voornaam + "!";
        naam = obj.voornaam + " " + obj.achternaam;
      }

      document.getElementById("gebruikersnaam").innerHTML = naam;
      document.getElementById("gebruikersnaam2").innerHTML = voornaam;

    }
    xhr.open("GET", "http://localhost:8082/api/users/" + localStorage.getItem("userId"), true);
    xhr.setRequestHeader("Authorization", localStorage.getItem("token"));
    xhr.send();
  }