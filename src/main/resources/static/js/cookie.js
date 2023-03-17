export const csrfToken = () => {
    console.log("document.cookie: " + document.cookie)
    const {name, value} = document.cookie
        .split(";")
        .map(cookieEntry => {
            const array = cookieEntry.trim().split("=").map(string => string.trim());
            const cookieName = array[0];
            const cookieValue = array[1];
            return {name: cookieName, value: cookieValue};
        })
        .find(cookieObject => cookieObject.name === "XSRF-TOKEN");
    return {[`X-${name}`]: value};
}