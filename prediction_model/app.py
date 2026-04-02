from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
import numpy as np
import pickle
import uvicorn

app = FastAPI(title="Disease Prediction API")

# Load model
with open("classifier.pkl", "rb") as f:
    model = pickle.load(f)

print("✅ Model loaded successfully")
print("Expected features:", model.n_features_in_)

# Input schema
class InputData(BaseModel):
    features: List[int]

# Root API
@app.get("/")
def home():
    return {"message": "Hello Karthik, API is running 🚀"}

# Prediction API
@app.post("/predict")
def predict(data: InputData):
    try:
        input_array = np.array(data.features).reshape(1, -1)

        if input_array.shape[1] != model.n_features_in_:
            return {
                "error": f"Expected {model.n_features_in_} features, got {input_array.shape[1]}"
            }

        prediction = model.predict(input_array)
        proba = model.predict_proba(input_array)

        return {
            "prediction": str(prediction[0]),
            "confidence": float(max(proba[0]))
        }

    except Exception as e:
        return {"error": str(e)}

# Run server
if __name__ == "__main__":
    uvicorn.run("main:app", host="127.0.0.1", port=8000, reload=True)