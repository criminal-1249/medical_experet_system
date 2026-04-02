use medical_diagnosis_db;

INSERT INTO treatments (disease_id, medication, advice) VALUES

-- 1 Fungal infection
(1, 'Clotrimazole cream', 'Keep affected area clean and dry'),

-- 2 Allergy
(2, 'Antihistamines', 'Avoid allergens and keep surroundings clean'),

-- 3 GERD
(3, 'Omeprazole', 'Avoid spicy food and eat smaller meals'),

-- 4 Chronic cholestasis
(4, 'Ursodeoxycholic acid', 'Avoid alcohol and fatty foods'),

-- 5 Drug Reaction
(5, 'Antihistamines', 'Stop the triggering drug immediately'),

-- 6 Peptic ulcer disease
(6, 'Proton pump inhibitors', 'Avoid NSAIDs and spicy food'),

-- 7 AIDS
(7, 'Antiretroviral therapy', 'Maintain hygiene and regular monitoring'),

-- 8 Diabetes
(8, 'Metformin / Insulin', 'Control diet and exercise regularly'),

-- 9 Gastroenteritis
(9, 'ORS, Antiemetics', 'Stay hydrated and avoid contaminated food'),

-- 10 Bronchial Asthma
(10, 'Inhaled bronchodilators', 'Avoid triggers like dust and smoke'),

-- 11 Hypertension
(11, 'Amlodipine', 'Reduce salt intake and exercise'),

-- 12 Migraine
(12, 'Pain relievers (Ibuprofen)', 'Avoid stress and maintain sleep'),

-- 13 Cervical spondylosis
(13, 'Muscle relaxants', 'Do neck exercises and maintain posture'),

-- 14 Paralysis (brain hemorrhage)
(14, 'Emergency care', 'Immediate hospitalization required'),

-- 15 Jaundice
(15, 'Supportive care', 'Avoid alcohol and take rest'),

-- 16 Malaria
(16, 'Antimalarial drugs (Artemisinin)', 'Avoid mosquito exposure'),

-- 17 Chicken pox
(17, 'Antiviral drugs', 'Avoid scratching and maintain hygiene'),

-- 18 Dengue
(18, 'Paracetamol', 'Stay hydrated and avoid NSAIDs'),

-- 19 Typhoid
(19, 'Antibiotics (Ciprofloxacin)', 'Drink clean water and rest'),

-- 20 Hepatitis A
(20, 'Supportive care', 'Maintain hygiene and avoid alcohol'),

-- 21 Hepatitis B
(21, 'Antiviral medication', 'Regular monitoring required'),

-- 22 Hepatitis C
(22, 'Direct-acting antivirals', 'Avoid alcohol'),

-- 23 Hepatitis D
(23, 'Interferon therapy', 'Monitor liver function'),

-- 24 Hepatitis E
(24, 'Supportive care', 'Drink clean water'),

-- 25 Alcoholic hepatitis
(25, 'Corticosteroids', 'Stop alcohol consumption'),

-- 26 Tuberculosis
(26, 'Isoniazid, Rifampicin', 'Complete full course of treatment'),

-- 27 Common Cold
(27, 'Paracetamol', 'Rest and drink fluids'),

-- 28 Pneumonia
(28, 'Antibiotics', 'Seek medical care if severe'),

-- 29 Piles
(29, 'Stool softeners', 'High fiber diet'),

-- 30 Heart attack
(30, 'Aspirin, Nitroglycerin', 'Immediate emergency care'),

-- 31 Varicose veins
(31, 'Compression stockings', 'Avoid prolonged standing'),

-- 32 Hypothyroidism
(32, 'Levothyroxine', 'Regular thyroid monitoring'),

-- 33 Hyperthyroidism
(33, 'Methimazole', 'Monitor thyroid levels'),

-- 34 Hypoglycemia
(34, 'Glucose tablets', 'Eat frequent small meals'),

-- 35 Osteoarthritis
(35, 'Pain relievers', 'Exercise regularly'),

-- 36 Arthritis
(36, 'NSAIDs', 'Physical therapy'),

-- 37 Vertigo
(37, 'Meclizine', 'Avoid sudden head movements'),

-- 38 Acne
(38, 'Benzoyl peroxide', 'Keep skin clean'),

-- 39 Urinary tract infection
(39, 'Antibiotics', 'Drink plenty of water'),

-- 40 Psoriasis
(40, 'Topical corticosteroids', 'Moisturize skin'),

-- 41 Impetigo
(41, 'Topical antibiotics', 'Maintain hygiene');

use medical_diagnosis_db;
select symptom_name from symptoms
order by symptom_id asc;

use medical_diagnosis_db;
select * from diseases 
where disease_name = "AIDS";