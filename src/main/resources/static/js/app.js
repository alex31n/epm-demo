
function calculateAge(birthdate) {
    const date = moment(birthdate);
    const now = moment();
    return now.diff(date,'years')
}