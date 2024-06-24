const baseUrl = "http://localhost:9090";
const post = async (url, data) => {
  url = baseUrl + url;
  const res = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  console.log(res);
  const json = await res.json();
  if (!res.ok) {
    console.error("Error:", res.status);
    throw new Error(`Error: ${res.status} - ${json.message}`);
  }
  return json;
};

export { post };
