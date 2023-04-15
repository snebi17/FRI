let isCategory = false;
let currentCategory = [];
let currentCategoryId;
let entries;

// Add card modal
const openModal = document.getElementById("add-fab");
openModal.addEventListener("click", () => {
	let modal = document.createElement("div");
	modal.classList.add("add-modal");
	showModal(modal);
});

const sortCategories = document.getElementById("sort-fab");
sortCategories.addEventListener("click", () => {
	let inventory = JSON.parse(localStorage.getItem("inventory"));

	const sorted = inventory.sort(function (a, b) {
		let first = Object.keys(a)[0];
		let second = Object.keys(b)[0];
		return first < second ? -1 : first > second ? 1 : 0;
	});

	localStorage.setItem("inventory", JSON.stringify(sorted));

	let categories = document.getElementById("categories");
	removeChildrenFromDOM(categories);
	getEntries();
	renderCategories();
});

// Exit modal button
const exitModal = document.getElementById("exit-modal");

window.onload = () => {
	currentCategory = JSON.parse(localStorage.getItem("category") || "[]");
	setup();
};

function setup() {
	getEntries();
	if (isCategory) {
		renderCategory();
	} else {
		renderCategories();
	}
}

function getEntries() {
	entries = Object.entries(
		JSON.parse(localStorage.getItem("inventory") || "{}")
	);
}

function showModal(modal) {
	let exit = document.createElement("div");
	exit.classList.add("exit-modal");
	let li = document.createElement("li");
	li.style = "cursor: pointer; color: white;";
	li.addEventListener("click", removeModal);
	li.classList.add("fa-solid", "fa-x");
	exit.appendChild(li);

	let form = document.createElement("form");
	form.classList.add("add-form");

	if (!isCategory) {
		let input = document.createElement("input");
		input.classList.add("form-control");
		input.id = "add-form-input";
		let label = document.createElement("label");
		label.htmlFor = input.id;
		label.innerText = "Category: ";
		label.appendChild(input);
		let addBtn = document.createElement("button");
		addBtn.textContent = "Add";
		addBtn.classList.add("btn");
		addBtn.addEventListener("click", addCardToInventory);
		form.appendChild(label);
		form.appendChild(addBtn);
	} else {
		let input = document.createElement("input");
		input.classList.add("form-control");
		input.id = "question";
		let label = document.createElement("label");
		label.htmlFor = input.id;
		label.innerText = "Question: ";
		label.appendChild(input);
		form.appendChild(label);
		for (let i = 0; i < 3; i++) {
			let inputGroup = document.createElement("div");
			inputGroup.classList.add("input-group");
			let answer = document.createElement("input");
			answer.classList.add("form-control");
			answer.id = i;
			let label = document.createElement("label");
			label.htmlFor = answer.id;
			label.innerText = "Answer #" + (i + 1);
			let _true = document.createElement("input");
			let _false = document.createElement("input");
			_true.type = _false.type = "radio";
			_true.name = _false.name = i;
			inputGroup.appendChild(answer);
			inputGroup.appendChild(_true);
			inputGroup.appendChild(_false);
			label.appendChild(inputGroup);
			form.appendChild(label);
		}
		let addBtn = document.createElement("button");
		addBtn.textContent = "Add";
		addBtn.classList.add("btn");
		addBtn.addEventListener("click", addCardToCategory);
		form.appendChild(addBtn);
		console.log(form);
	}

	modal.appendChild(exit);
	modal.appendChild(form);
	document.body.appendChild(modal);
}

function removeModal() {
	let modal = document.getElementsByClassName("add-modal")[0];
	document.body.removeChild(modal);
}

function addCardToInventory(e) {
	let formInput = document.getElementById("add-form-input").value;
	if (formInput != "") {
		let inventory = JSON.parse(localStorage.getItem("inventory") || "[]");
		inventory.push({ [formInput]: [] });
		localStorage.setItem("inventory", JSON.stringify(inventory));
	} else {
		e.preventDefault();
		if (e.target.parentNode.getElementsByTagName("small")[0]) {
			return;
		}
		let error = document.createElement("small");
		error.classList.add("error");
		error.textContent = "Card category should not be empty!";
		e.target.parentNode.appendChild(error);
	}
	renderCards();
}

function addCardToCategory(e) {
	e.preventDefault();
	let question = document.getElementById("question").value;
	if (question == "") {
		if (e.target.parentNode.getElementsByTagName("small")[0]) {
			return;
		}
		let error = document.createElement("small");
		error.classList.add("error");
		error.textContent = "Question should not be an empty string!";
		e.target.parentNode.appendChild(error);
		return;
	}
	let el = e.target.parentNode.getElementsByTagName("small")[0];
	if (el) {
		e.target.parentNode.removeChild(el);
	}
	let answers = [];
	let inputs = document.getElementsByClassName("form-control");
	for (let i = 0; i < 3; i++) {
		let answer = inputs[i + 1].value;
		let trueFalse = document.getElementsByName(i);
		let validity;
		if (!trueFalse[0].checked && !trueFalse[1].checked) {
			let error = document.createElement("small");
			error.classList.add("error");
			error.textContent = "Answer is whether true or false!";
			e.target.parentNode.appendChild(error);
			return;
		} else if (trueFalse[0].checked) {
			validity = "true";
		} else {
			validity = "false";
		}
		answers.push({ [answer]: validity });
	}

	Object.values(currentCategory)[0].push({ [question]: answers });
	localStorage.setItem("category", JSON.stringify(currentCategory));
	let inventory = JSON.parse(localStorage.getItem("inventory"));
	inventory[currentCategoryId] = currentCategory;
	localStorage.setItem("inventory", JSON.stringify(inventory));
	renderCategory();
}

function renderCategory() {
	isCategory = true;
	let cards = document.getElementById("cards");

	if (cards.childElementCount > 0) {
		removeChildrenFromDOM(cards);
	}

	if (cards.classList.contains("display-none")) {
		cards.classList.remove("display-none");
	}

	createBackButton();

	let noOfQuestions = Object.values(currentCategory)[0].length;
	if (
		!noOfQuestions &&
		!document.body.getElementsByClassName("zero-cards")[0]
	) {
		let div = document.createElement("div");
		div.classList.add("zero-cards");
		let textNode = document.createTextNode(
			"There are zero cards in this category!"
		);
		div.appendChild(textNode);
		cards.appendChild(div);
	} else {
		let zero = document.getElementsByClassName("zero-cards")[0];
		if (zero) {
			cards.removeChild(zero);
		}
		Object.values(currentCategory).forEach((card) => {
			Object.values(card).forEach((obj, i) => {
				const [question, answers] = Object.entries(obj)[0];
				let card = document.createElement("div");
				card.id = i;
				card.classList.add("grid-item");
				card.addEventListener("click", (e) => {
					let id = e.currentTarget.id;
					console.log(currentCategory);
					Object.values(currentCategory)[0].splice(id, 1);
					localStorage.setItem(
						"category",
						JSON.stringify(currentCategory)
					);
					let inventory = JSON.parse(
						localStorage.getItem("inventory")
					);
					inventory[currentCategoryId] = currentCategory;
					localStorage.setItem(
						"inventory",
						JSON.stringify(inventory)
					);
					renderCategory();
				});
				let p = document.createElement("p");
				p.textContent = question;
				p.style = "text-align: center;";
				card.appendChild(p);

				console.log(Object.values(answers));
				answers.forEach((answer) => {
					let [a, v] = Object.entries(answer)[0];
					let small = document.createElement("small");
					small.textContent = a;
					if (v == "true") {
						small.style = "color: green";
					} else {
						small.style = "color: red";
					}
					card.appendChild(small);
				});
				cards.appendChild(card);
			});
		});
	}
}

function createBackButton() {
	let backBtn = document.createElement("button");
	backBtn.classList.add("back-btn");
	let li = document.createElement("li");
	li.classList.add("fa-solid", "fa-arrow-left");
	backBtn.append(li);
	backBtn.addEventListener("click", () => {
		document.body.removeChild(backBtn);
		let cards = document.getElementById("cards");
		renderCategories();
		removeChildrenFromDOM(cards);
	});
	document.body.appendChild(backBtn);
}

function renderCategories() {
	isCategory = false;
	let categories = document.getElementById("categories");

	if (categories.classList.contains("display-none")) {
		categories.classList.remove("display-none");
	}

	entries.forEach((entry) => {
		let card = document.createElement("div");
		card.classList.add("grid-item");
		card.addEventListener("click", () => {
			currentCategoryId = entry[0];
			currentCategory = Object.values(entry)[1];
			localStorage.setItem("category", JSON.stringify(currentCategory));
			categories.classList.add("display-none");
			renderCategory();
			removeChildrenFromDOM(categories);
		});
		let p = document.createElement("h2");
		p.textContent = Object.keys(entry[1]).toString();
		let small = document.createElement("small");
		small.textContent =
			"No. of cards: " + Object.values(entry[1])[0].length;
		card.appendChild(p);
		card.appendChild(small);
		categories.appendChild(card);
	});
}

function removeChildrenFromDOM(parent) {
	while (parent.firstChild) {
		parent.removeChild(parent.firstChild);
	}
}
