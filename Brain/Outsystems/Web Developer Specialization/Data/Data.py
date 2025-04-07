import pandas as pd
import random

# Define the file name
file_name = "large_dataset.xlsx"

# Generate data in bulk for better performance
num_rows = 100
data = {
    "Attribute1": [f"Attribute_{i}" for i in range(1, num_rows + 1)],
    "IsArchived": [False] * num_rows,
    "Year": [random.randint(2000, 2025) for _ in range(num_rows)]
}

# Create a DataFrame
df = pd.DataFrame(data)

# Save to Excel using xlsxwriter for efficiency
df.to_excel(file_name, index=False, engine='xlsxwriter')

print(f"File '{file_name}' has been created with {num_rows} rows.")



