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
  };
  xhr.open(
    "GET",
    "http://173.212.208.199:1337/api/users/" + localStorage.getItem("userId"),
    true
  );
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
  };
  xhr.open(
    "GET",
    "http://173.212.208.199:1337/api/users/bedrijf/" +
      localStorage.getItem("userId"),
    true
  );
  xhr.setRequestHeader("Authorization", localStorage.getItem("token"));
  xhr.send();
}

const logout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("userId");
  localStorage.removeItem("userRole");
  window.location.href = "../inlogscherm.html";
};

const loginAuthAdmin = () => {
  if (localStorage.getItem("userRole") != "[ROLE_ADMIN]")
    window.location.href = "../inlogscherm.html";
};
