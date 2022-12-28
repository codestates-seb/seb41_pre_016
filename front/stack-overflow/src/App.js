import React, { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';
import TagsPage from './pages/TagsPage/index';
import Users from './pages/Users/index';

const App = () => {
  // const URL = process.env.REACT_APP_API_URL;

  // const [data, setData] = useState();
  // const [loading, setLoading] = useState(false);

  // useEffect(() => {
  //   const fetchData = async () => {
  //     setLoading(true);

  //     try {
  //       const response = await axios.get(URL);
  //       console.log(response);
  //       setData(response);
  //     } catch (e) {
  //       console.log(e);
  //     }
  //     setLoading(false);
  //   };

  //   fetchData();
  // }, []);

  return <Users />;
};

export default App;
