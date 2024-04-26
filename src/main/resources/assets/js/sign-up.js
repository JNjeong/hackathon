document.addEventListener('DOMContentLoaded', function() {
    // Get references to the radio buttons and hidden input
    var radioStudent = document.getElementById('radio_student');
    var radioProfessor = document.getElementById('radio_professor');
    var userTypeHiddenInput = document.getElementById('user_type');

    // Function to update hidden input based on the selected radio button
    function updateUserType() {
        if (radioStudent.checked) {
            userTypeHiddenInput.value = "student";
        } else if (radioProfessor.checked) {
            userTypeHiddenInput.value = "professor";
        }
    }

    // Event listeners for radio buttons
    radioStudent.addEventListener('change', updateUserType);
    radioProfessor.addEventListener('change', updateUserType);
});
