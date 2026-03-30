console.log("JS FILE LOADED");

let students = [];

// ================= LOAD STUDENTS =================
function loadStudents() {
    fetch("/students")
        .then(res => res.json())
        .then(data => {
            students = data;
            displayStudents(data);
        });
}

// ================= DISPLAY =================
function displayStudents(data) {
    let table = document.getElementById("studentTable");
    table.innerHTML = "";

    data.forEach(s => {
        table.innerHTML += `
        <tr>
            <td>${s.name}</td>
            <td>${s.email}</td>
            <td>${s.courses ? s.courses.map(c => c.courseName).join(", ") : ""}</td>
            <td>
                <i class="fa fa-edit" onclick="goToEdit(${s.id})"></i>
                <i class="fa fa-trash" onclick="deleteStudent(${s.id})"></i>
            </td>
        </tr>`;
    });
}
// ================= NAVIGATION =================
function goToAdd() {
    window.location.href = "add-student.html";
}

function goToEdit(id) {
    window.location.href = "edit-student.html?id=" + id;
}

function goHome() {
    window.location.href = "index.html";
}

function goToCourse(){
	window.location.href = "course-add.html"
}

// ================= ADD =================
function addStudent() {
    let student = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value
    };

    fetch("/students", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(student)
    })
    .then(() => goHome());
}

// ================= LOAD FOR EDIT =================
function loadStudentForEdit() {
    let params = new URLSearchParams(window.location.search);
    let id = params.get("id");

    fetch("/students/" + id)
        .then(res => res.json())
        .then(s => {
            document.getElementById("id").value = s.id;
            document.getElementById("name").value = s.name;
            document.getElementById("email").value = s.email;
        });
}

// ================= UPDATE =================
function updateStudent() {
    let id = document.getElementById("id").value;

    let student = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value
    };

    fetch("/students/" + id, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(student)
    })
    .then(() => goHome());
}

// ================= DELETE =================
function deleteStudent(id) {
    if (confirm("Are you sure to delete?")) {
        fetch("/students/" + id, {
            method: "DELETE"
        })
        .then(() => loadStudents());
    }
}

// ================= SEARCH =================
function searchStudent() {
    let keyword = document.getElementById("search").value.toLowerCase();

    let filtered = students.filter(s =>
        s.name.toLowerCase().includes(keyword)
    );

    displayStudents(filtered);
}

// ================= AUTO LOAD =================
if (window.location.pathname.includes("index.html")) {
    window.onload = loadStudents;
}

if (window.location.pathname.includes("edit-student.html")) {
    window.onload = loadStudentForEdit;
}

// ================= DROPDOWN =================
function loadStudentsDropdown() {
    fetch("/students")
        .then(res => res.json())
        .then(data => {
            let dropdown = document.getElementById("studentSelect");
            dropdown.innerHTML = '<option value="">Select Student</option>';

            data.forEach(s => {
                let option = document.createElement("option");
                option.value = s.id;
                option.textContent = s.name;
                dropdown.appendChild(option);
            });
        });
}

// ================= COURSE ADD =================
function addCourse() {
    let courseName = document.getElementById("courseName").value;
    let studentId = document.getElementById("studentSelect").value;

    let course = {
        courseName: courseName,
        studentId: studentId
    };

    fetch("/courses", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(course)
    })
    .then(() => {
        alert("Course added successfully!");
        goHome();
    });
	
	
	console.log("BUTTON CLICKED");   // ✅ must print

	   
	    console.log(courseName, studentId);  // ✅ check values
}

// ================= LOAD COURSES =================
function loadCourses() {
    fetch("/courses")
        .then(res => res.json())
        .then(data => displayCourses(data));
		console.log(data); 
}

// ================= DISPLAY COURSES =================
/*function displayCourses(data) {
    let table = document.getElementById("courseTable");
    table.innerHTML = "";

    data.forEach(c => {
		
		console.log(c);
		
        table.innerHTML += `
        <tr>
            <td>${c.courseName}</td>
            <td>${c.student ? c.student.name : ""}</td>
            <td>
                <i class="fa fa-trash" onclick="deleteCourse(${c.id})"></i>
            </td>
        </tr>`;
    });
}*/

function displayCourses(data) {
    let table = document.getElementById("courseTable");
    table.innerHTML = "";

    data.forEach(c => {
        console.log(c); // debug
		
		if (!table) {
		       console.log("TABLE NOT FOUND ❌");
		       return;
		   }

        table.innerHTML += `
        <tr>
            <td>${c.courseName || ""}</td>
            <td>${c.student?.name || "No Student"}</td>
            <td>
                <i onclick="deleteCourse(${c.id})">Delete</i>
            </td>
        </tr>`;
    });
}

// ================= DELETE COURSE =================
function deleteCourse(id) {
    if (confirm("Delete this course?")) {
        fetch("/courses/" + id, {
            method: "DELETE"
        }).then(() => loadCourses());
    }
}

// ================= NAVIGATION =================
function goToCourse() {
    window.location.href = "course-add.html";
}

// ================= AUTO LOAD =================
if (window.location.pathname.includes("course-list.html")) {
    window.onload = loadCourses;
}

if (window.location.pathname.includes("course-add.html")) {
    window.onload = loadStudentsDropdown;
}
