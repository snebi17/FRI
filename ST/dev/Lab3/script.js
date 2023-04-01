//"use strict";
let identifier = 0;

function domRemoveParticipant(event) {
    let parent = event.target.parentNode;
    let name = parent.firstChild.innerText;
	if (confirm("Are you sure you want to delete " + name + "?")) {
		document.getElementById("participant-table").removeChild(parent);
        let participants = JSON.parse(localStorage.getItem("participants"));
        participants = participants.filter(participant => participant.id != parent.id);
        console.log(participants);
        localStorage.setItem("participants", JSON.stringify(participants));
	}
}

function domAddParticipant(participant) {
	let table = document.getElementById("participant-table");
	let tr = document.createElement("tr");
    tr.id = participant.id;
	tr.addEventListener("dblclick", (event) => {
		domRemoveParticipant(event);
	});

	Object.values(participant).forEach((value) => {
        if (!Number.isInteger(value)) {
            let td = document.createElement("td");
            let text = document.createTextNode(value);
            td.appendChild(text);
            tr.appendChild(td);
        }
	});

	table.appendChild(tr);
}

function addParticipant(event) {

	// TODO: Get values
	const first = document.getElementById("first").value;
	const last = document.getElementById("last").value;
	const role = document.getElementById("role").value;

    if (first === "" || last === "") {
        return;
    }

	document.getElementById("first").value = "";
	document.getElementById("last").value = "";

	// Create participant object
	const participant = {
        id: identifier++,
		first: first,
		last: last,
		role: role,
	};

    // Adding the participant to localStorage
    let participants = JSON.parse(localStorage.getItem("participants") || "[]");
    participants.push(participant);
    localStorage.setItem("participants", JSON.stringify(participants));

	// Add participant to the HTML
	domAddParticipant(participant);

	// Move cursor to the first name input field
	document.getElementById("first").focus();
    document.getElementById("role").lastElementChild.selected = true;
}

document.addEventListener("DOMContentLoaded", () => {
	// This function is run after the page contents have been loaded
	// Put your initialization code here
    let participants = JSON.parse(localStorage.getItem("participants") || "[]");
    identifier = participants.length;
    Object.values(participants).forEach((value) => {
        domAddParticipant(value);
    })
	document.getElementById("addButton").onclick = addParticipant;
});

// The jQuery way of doing it
$(document).ready(() => {
	// Alternatively, you can use jQuery to achieve the same result
});
