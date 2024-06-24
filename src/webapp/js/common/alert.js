const displayMessage = (message, infoTagId, type) => {
  const infoP = document.getElementById(infoTagId);
  infoP.style.display = "block";
  infoP.classList.remove("alert-info");
  infoP.classList.remove("alert-success");
  infoP.classList.remove("alert-danger");
  infoP.classList.remove("alert-warning");
  if (type === "error") {
    infoP.classList.add("alert-danger");
  } else if (type === "success") {
    infoP.classList.add("alert-success");
  } else if (type === "warning") {
    infoP.classList.add("alert-warning");
  } else {
    infoP.classList.add("alert-info");
  }

  infoP.innerHTML = message;
  countDown(infoTagId);
};

function hideInfo(errorTagId = "form-info") {
  const errorP = document.getElementById(errorTagId);
  errorP.style.display = "none";
  errorP.innerHTML = "";
}

const countDown = (infoTagId) => {
  console.log("countdown");
  setTimeout(() => {
    console.log("countdown");
    hideInfo(infoTagId);
  }, 2000);
};

function displayError(message, errorTagId = "form-info") {
  displayMessage(message, errorTagId, "error");
}

function displaySuccess(message, errorTagId = "form-info") {
  displayMessage(message, errorTagId, "success");
}

function displayWarning(message, errorTagId = "form-info") {
  displayMessage(message, errorTagId, "warning");
}

function displayInfo(message, errorTagId = "form-info") {
  displayMessage(message, errorTagId, "info");
}

export default {
  info: displayInfo,
  error: displayError,
  success: displaySuccess,
  warning: displayWarning,
  hide: hideInfo,
};
