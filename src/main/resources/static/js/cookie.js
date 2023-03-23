export const csrfToken = () => {
    const [name, value] = document.cookie.split(';')
        .map(cookie => cookie.trim().split('='))
        .find(cookie => cookie[0] === 'XSRF-TOKEN');

    console.log(name, value)
    return {[`X-${[name]}`]: value};
}
