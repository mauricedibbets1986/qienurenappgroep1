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

const deleteGebruiker = () => {
  var xhr = new XMLHttpRequest();
  xhr.open(
    "DELETE",
    "http://173.212.208.199:1337/api/users/" +
      document.getElementById("selectVerwijderGebruiker").value,
    true
  );
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.setRequestHeader("Authorization", localStorage.getItem("token"));
  xhr.send();
};

const loginAuthAdmin = () => {
  if (localStorage.getItem("userRole") != "[ROLE_ADMIN]")
    window.location.href = "../inlogscherm.html";
};

const alleBedrijvenModal = () => {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
      var bedrijven = JSON.parse(this.responseText);
      var x;
      var select = document.getElementById("selectBedrijf");
      for (x = 0; x < bedrijven.length; x++) {
        var el = document.createElement("option");
        el.textContent = bedrijven[x].bedrijfsNaam;
        el.value = bedrijven[x].id;
        select.appendChild(el);
      }
    }
  };
  xhr.open("GET", "http://173.212.208.199:1337/api/bedrijf/all", true);
  xhr.setRequestHeader("Authorization", localStorage.getItem("token"));
  xhr.send();
};

const GebruikersVerwijderenModal = () => {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
      var databaseContents = JSON.parse(this.responseText);
      var x;
      var select = document.getElementById("selectVerwijderGebruiker");
      for (x = 0; x < databaseContents.length; x++) {
        var el = document.createElement("option");
        el.textContent =
          databaseContents[x].voornaam + " " + databaseContents[x].achternaam;
        el.value = databaseContents[x].userId;
        select.appendChild(el);
      }
    }
  };
  xhr.open("GET", "http://173.212.208.199:1337/api/admin/all-users", true);
  xhr.setRequestHeader("Authorization", localStorage.getItem("token"));
  xhr.send();
};

const alleGebruikersModal = () => {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
      var databaseContents = JSON.parse(this.responseText);

      var x;
      var c;
      var select = document.getElementById("selectGebruiker");
      var selectverw = document.getElementById("selectVerwijderGebruiker");

      for (x = 0; x < databaseContents.length; x++) {
        for (c = 0; c < databaseContents[x].roles.length; c++) {
          if (
            databaseContents[x].roles[c].name == "ROLE_ADMIN" ||
            databaseContents[x].roles[c].name == "ROLE_BEDRIJF" ||
            databaseContents[x].roles[c].name == "ROLE_MEDEWERKER"
          ) {
          } else {
            var el = document.createElement("option");
            el.textContent =
              databaseContents[x].voornaam +
              " " +
              databaseContents[x].achternaam;
            el.value = databaseContents[x].id;
            select.appendChild(el);
          }
        }
      }
    }
  };
  xhr.open("GET", "http://173.212.208.199:1337/api/admin/all-users", true);
  xhr.setRequestHeader("Authorization", localStorage.getItem("token"));
  xhr.send();
};

const updateBedrijftoGebruiker = () => {
  var xhr = new XMLHttpRequest();
  xhr.open(
    "PUT",
    "http://173.212.208.199:1337/api/bedrijf/" +
      document.getElementById("selectBedrijf").value +
      "/" +
      document.getElementById("selectGebruiker").value,
    true
  );
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.setRequestHeader("Authorization", localStorage.getItem("token"));
  xhr.send();
};
