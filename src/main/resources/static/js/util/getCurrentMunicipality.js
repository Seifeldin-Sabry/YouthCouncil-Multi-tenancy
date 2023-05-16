export default function getCurrentMunicipality() {
    const url = new URL(window.location.href);
    return url.pathname.split('/')[1];
}
