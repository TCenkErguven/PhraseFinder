import React, { useEffect, useState } from "react";
import axios from "axios";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import SearchIcon from "@mui/icons-material/Search";
import DirectionsRunIcon from "@mui/icons-material/DirectionsRun";
import "./mainPage.css";

export default function MainPage() {
  const [searchedPhrase, setSearchedPhrase] = useState("");
  const [author, setAuthor] = useState("Let's Find The Author");

  function handleSubmit() {
    console.log(searchedPhrase);
    if (searchedPhrase.length !== 0) {
      const formData = {
        searchedPhrase: searchedPhrase,
      };
      console.log(formData);

      axios
        .post("http://localhost:9085/api/v1/word-holder/get-author", formData)
        .then((response) => {
          console.log(response);
          setAuthor(`Your Author is ${response.data}`);
          setSearchedPhrase("");
        })
        .catch((error) => {
          console.log(error);
          setAuthor("None Wrote Such A Phrase");
        });
    } else {
      setAuthor("Please, Make A Phrase Entry");
    }
  }

  return (
    <>
      <div className="frame">
        <div className="header">
          <div style={{ display: "flex", alignItems: "center" }}>
            <a>Author Finder</a>
            <DirectionsRunIcon sx={{ height: "6rem" }} />
          </div>
        </div>
        <div className="background">
          <div className="contentCard">
            <p>{author}</p>
            <div>
              <TextField
                id="filled-multiline-static"
                label="Enter The Phrase"
                multiline
                rows={15}
                variant="filled"
                value={searchedPhrase}
                onChange={(e) => {
                  setSearchedPhrase(e.target.value);
                }}
                sx={{
                  width: "100rem",
                  marginBottom: "5%",
                  textAlign: "center",
                }}
              />
            </div>
            <div>
              <Button
                variant="contained"
                sx={{
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  backgroundColor: "#7D7C7C",
                }}
                onClick={handleSubmit}
              >
                Search
                <SearchIcon sx={{ ml: 1 }} />
              </Button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
