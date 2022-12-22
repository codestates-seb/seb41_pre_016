import React, { useState, useEffect } from "react";
import "./App.css";
import axios from "axios";
import Questions from "./Questions/Questions";
import { Reset } from "styled-reset";

const App = () => {
  const URL = "/test";

  const [data, setData] = useState();
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);

      try {
        const response = await axios.get(URL);
        console.log(response);
        setData(response);
      } catch (e) {
        console.log(e);
      }
      setLoading(false);
    };

    fetchData();
  }, []);

  return (
    <div>
      <Reset />
      <Questions />
    </div>
  );
};

export default App;
