const exams = document.getElementById("exams");
const quizzes = document.getElementById("quizzes");
const attendance = document.getElementById("attendance");
const container = document.getElementById("list");

document.getElementById('assignments').addEventListener('click', function() {
    const container = document.getElementById('cardContainer');
    container.innerHTML = ''; // Clear previous content if any

    for (let i = 1; i <= 5; i++) {
        const cardHTML = `
            <div class="card card-plain h-100" id="exam${i}">
                <div class="card-body p-3">
                    <ul class="list-group">
                        <li class="list-group-item border-0 d-flex align-items-center px-0 mb-2 pt-0">
                            <div class="d-flex align-items-start flex-column justify-content-center">
                                <h4 class="mb-2 btn btn-light" type="button">Exam ${i}</h4>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        `;
        container.insertAdjacentHTML('beforeend', cardHTML);
    }
});

exams.addEventListener("click", () => {
    fetch("url")
        .then((res) => res.json())
        .then((data) => {
            let count = 1;
            data.forEach((element) => {
                let examList = `
                <div class="card card-plain h-100" id="exam${count}">
                <div class="card-body p-3">
                    <ul class="list-group">
                        <li
                            class="list-group-item border-0 d-flex align-items-center px-0 mb-2 pt-0"
                        >
                            <div
                                class="d-flex align-items-start flex-column justify-content-center"
                            >
                                <h4
                                    class="mb-2 btn btn-light"
                                    type="button"
                                >
                                    Exam ${count}
                                </h4>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
                `;
                $("#list").append(examList);
                count++;
            });
        });
});

quizzes.addEventListener("click", () => {
    fetch("url")
        .then((res) => res.json())
        .then((data) => {
            data.forEach((element) => {
                let quizList = `
                <div class="card card-plain h-100">
                <div class="card-body p-3">
                    <ul class="list-group">
                        <li
                            class="list-group-item border-0 d-flex align-items-center px-0 mb-2 pt-0"
                        >
                            <div
                                class="d-flex align-items-start flex-column justify-content-center"
                            >
                                <h4
                                    class="mb-2 btn btn-light"
                                    type="button"
                                >
                                    Quiz 1
                                </h4>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
                `;
                $("#list").append(quizList);
            });
        });
});

attendance.addEventListener("click", () => {
    fetch("url")
        .then((res) => res.json())
        .then((data) => {
            data.forEach((element) => {
                let attendanceList = `
                <div class="card card-plain h-100">
                <div class="card-body p-3">
                    <ul class="list-group">
                        <li
                            class="list-group-item border-0 d-flex align-items-center px-0 mb-2 pt-0"
                        >
                            <div
                                class="d-flex align-items-start flex-column justify-content-center"
                            >
                                <h4
                                    class="mb-2 btn btn-light"
                                    type="button"
                                >
                                    Attendance 1
                                </h4>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
                `;
                $("#list").append(attendanceList);
            });
        });
});
