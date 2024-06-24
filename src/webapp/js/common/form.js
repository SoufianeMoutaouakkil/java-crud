const jsonFromForm = (form) => {
    const data = new FormData(form);
    const json = {};
    data.forEach((value, key) => {
        json[key] = value;
    });
    return json;
};

export { jsonFromForm };
