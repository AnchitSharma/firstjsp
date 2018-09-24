function onDateSubmit(obj) {
    var dobstr = obj.value;
    console.log(dobstr);
    if (dobstr !== null) {
        var dob = new Date(dobstr);
        console.log(dob);
        var age = (Date.now() - dob) / 31557600000;
        document.getElementById("age").value = Math.round(age);
    }
}
function onCitySelect(obj) {
    var city = obj.options[obj.selectedIndex].value;
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'cityc?city=' + city, true);
    xhr.overrideMimeType('application/json');
    xhr.onload = function () {
        if (this.status == 200) {
            var city = JSON.parse(this.responseText);
            document.getElementById("state").value = city.state;
            document.getElementById("country").value = city.country;
        }
    }
    xhr.send();
    console.log(city);
}

function checkDate() {

    var dojstr = document.getElementById('doj').value;
    if (dojstr !== '') {
        var doj = new Date(dojstr);
        var dobstr = document.getElementById('dob').value;
        var dob = new Date(dobstr);
        if (doj <= dob) {
            alert('date of joining is not less than date of birth.');
            return false;
        } else {
            return true;
        }
    } else {
        alert('Please Enter Valid Date Of Joining');
    }
}

function checkform() {

    if (!checkDate()) {
        console.log("in if check Date()")
        return false;
    }
    var a = document.getElementsByName("address");

    if (a[0].value.trim().length < 1) {
        alert('Please Enter Valid Address.');
        return false;
    }
    var csel = document.getElementById("city");
    var city = csel.options[csel.selectedIndex].text;
    if (city == 'Select City') {
        alert('Please Select a Valid City.');
        return false;
    }




    return true;
}

function selectUser() {
    
    var cls = document.getElementsByClassName("rec");
    for (var i = 0; i < cls.length; i++) {
        cls[i].removeAttribute("disabled");
    }

}

function editUser(){
    loadUser(getSelectedRow());
}
function getSelectedRow(){
    var checkboxes = document.getElementsByClassName('chbk');
    for(var i=0;i<checkboxes.length;i++){
        var state = checkboxes[i].checked;
        if(state){
            var currentRow = checkboxes[i].parentNode.parentNode;
            var secondcol =  currentRow.getElementsByTagName("td")[1];
            var user_id = secondcol.textContent;
            return parseInt(user_id);
        }
    }
}
function deleteUser(){
    var user_id = getSelectedRow();
    //delete the row from view
    //delete the row from database;
}
function loadUser(id) {
    var xhr = new XMLHttpRequest();
    if (id !== null) {
        xhr.open('GET', 'savedata?type=get&id=' + id, true);
        xhr.overrideMimeType('application/json');
        xhr.onload = function(){
            if (this.status == 200){
                console.log(this.responseText);
            }
        }
        xhr.send();
    }
}

