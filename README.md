# CarSim: Modular Manufacturing & Resource Engine
**A Java simulation engine focused on resource pipelines, state persistence, and data insights.**

## ⚙️ Technical Architecture
CarSim is built as a **multi-tiered system**, separates user interaction, economic logic, and data representation.

### 1. Producer-Consumer-Converter Pipeline
The core of the simulation utilizes advanced architectural patterns to manage complex resource dependencies:
* **Producers:** Logic units that generate raw materials (e.g., Iron, Crude Oil) based on "tick" cycles.
* **Converters:** Intermediate nodes that consume one or more input resources to create higher-tier components (e.g., Engines).
* **Consumers:** The final sink where assets are "sold" to generate the a race car that is used on the race track to generate credits required for system expansion.



### 2. Analytical Data Visualization (`graph` command)
To move beyond simple text output, I created a **dynamic graphing utility**. 
* **Functionality:** By parsing historical "Resource-per-Tick" data, the system generates visual trends of the economy's health.
* **Use Case:** This allows the user to perform **bottleneck analysis**—visually identifying when a Converter's production flatlines due to upstream supply shortages.

### 3. Command Pattern Architecture
The interface is driven by a custom **Command Parser**, separating user input from the core engine. This ensures that adding new functionality does not require modifying the simulation's internal logic.

---

## 🚀 Key Features
* **State Persistence:** Uses **Serialization** to save and load complex world states, ensuring full data integrity across sessions.
* **Deterministic Simulation:** Uses a global "Tick" system to ensure resource generation is consistent and unaffected by system hardware speed.
* **Modular Design:** Every entity inherits from a base `Machine` class, allowing for easy expansion and polymorphic behavior.

## 🛠️ Installation & Usage
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/JeremyM07/Car-Simulation.git](https://github.com/JeremyM07/Car-Simulation.git)
   ```
2. **Run the run.sh file**
   ```bash
   chmod +x run.sh
   ./run.sh
  ```

