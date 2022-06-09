create table resume_likes (
    user_id bigint not null references usr,
    resume_id bigint not null references resume,
    primary key (user_id, resume_id)
)