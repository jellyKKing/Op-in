import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";

import "./index.css";
import { RecoilRoot } from "recoil";

import Main from "./pages/Main";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RecoilRoot>
      <ToastContainer />
      <BrowserRouter>
        <Main />
      </BrowserRouter>
    </RecoilRoot>
  </React.StrictMode>
);
