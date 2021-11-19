-- :name create-vacancy! :! :n
-- :doc creates a new vacancy record
INSERT INTO vacancies
(title, description, location, salary_min, salary_max, remote_available, expired_at, created_at, updated_at)
VALUES
(:title, :description, :location, :salary-min, :salary-max, :remote-available, :expired-at, now(), now())

-- :name update-vacancy! :! :n
-- :doc updates an existing vacancy record
UPDATE vacancies
SET
  title = :title,
  description = :description,
  location = :location,
  salary_min = :salary-min,
  remote_available = :remote-available,
  moderated = :moderated,
  expired_at = :expired-at,
  updated_at = now()
WHERE id = :id

-- :name delete-vacancy! :! :n
-- :doc deletes a user record given the id
DELETE FROM vacancies WHERE id = :id

-- :name get-vacancy :? :1
-- :doc retrieves a vacancy record given the id
SELECT * FROM vacancies WHERE id = :id

-- :name get-last-vacancies :? :*
-- :doc retrieves last :limit vacancies
select * from vacancies
order by id desc
limit :limit

-- :name get-last-vacancy :? :1
-- :doc retrieves last vacancy
select * from vacancies
order by id desc
limit 1
