import { useEffect, useState } from "react";
import axios from "axios";

export const usePost = (url, id, dataObj) => {
  useEffect(() => {
    const fetch = async () => {
      try {
        await axios
          .post(`${url}${id}`, {
            title: data.title,
            content: data.content,
            tags: data.tags,
          })
          .then((res) => {
            console.log(res);
          })
          .then(() => {
            alert("글 작성");
            window.location.href = "/";
          });
      } catch (e) {
        console.log(e);
      }
    };
    fetch();
  }, []);
};
