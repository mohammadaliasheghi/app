document.addEventListener("DOMContentLoaded", () => {
    // Select all table rows in the tbody
    const rows = document.querySelectorAll(".user-table tbody tr");

    // Add a click event listener to each row
    rows.forEach(row => {
        row.addEventListener("click", () => {
            // Highlight the clicked row
            rows.forEach(r => r.classList.remove("highlight"));
            row.classList.add("highlight");

            // Gather user details from the clicked row
            const userDetails = Array.from(row.children).map(td => td.textContent);
            alert(`User Details:\n
            Full Name: ${userDetails[0]}\n
            Age: ${userDetails[1]}\n
            Employee: ${userDetails[2]}\n
            Gender: ${userDetails[3]}`);
        });
    });
});
